package be.cegeka;

import be.cegeka.domain.SummaryInView;
import com.couchbase.client.deps.io.netty.util.ResourceLeakDetector;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.document.json.JsonArray;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.env.CouchbaseEnvironment;
import com.couchbase.client.java.env.DefaultCouchbaseEnvironment;
import com.couchbase.client.java.view.AsyncViewResult;
import com.couchbase.client.java.view.AsyncViewRow;
import com.couchbase.client.java.view.Stale;
import com.couchbase.client.java.view.ViewQuery;
import org.apache.commons.lang3.tuple.Pair;
import rx.Observable;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Test program that indicates a leak in couchbase using large datasets and AsyncBuckets.
 */
public class CouchbaseLeak {

    private static final List<String> CLUSTER_HOSTS = Arrays.asList("valvconsultcbqas04", "valvconsultcbqas05", "valvconsultcbqas06");
    private static final String BUCKET = "orders";
    private static final String PWD = "";
    private static final String ORDER_DESIGN_DOC_NAME_5 = "Order5";
    private static final String ORDER_DESIGN_DOC_ALL_ORDERS_ORDERDATE_VIEW = "summary_all_orders_orderedByOrderDate";
    private static final int PAGE_SIZE = 50;

    public static void main(String[] args) {
        CouchbaseLeak leak = new CouchbaseLeak();
        leak.executeQuery();
    }

    private void executeQuery() {
        ResourceLeakDetector.setLevel(ResourceLeakDetector.Level.PARANOID);
        CouchbaseEnvironment couchbaseEnvironment = DefaultCouchbaseEnvironment
            .builder()
            .bootstrapCarrierEnabled(false)
            .autoreleaseAfter(5000)
            .connectTimeout(20000)
//                .queryEnabled(true)
            .queryTimeout(60000)
            .build();

        CouchbaseCluster couchbaseCluster = CouchbaseCluster.create(couchbaseEnvironment, CLUSTER_HOSTS);
        Bucket bucket = couchbaseCluster.openBucket(BUCKET, PWD);
        ViewQuery viewQuery = viewQueryFor("11740");

        int pageNum = 1;
        while (printPage(viewQuery, bucket, pageNum)) {
            pageNum += 1;
        }
    }

    private boolean printPage(ViewQuery viewQuery, Bucket bucket, int pageNumber) {
        // Execute paginated query.
        List<SummaryInView> summaryInViews = bucket.async()
            .query(viewQuery)
            .flatMap(this::extractRowsOrError)
            .map(val -> mapValueKeyPair(val, SummaryInView.class))
            .skip(pageNumber)
            .limit(PAGE_SIZE)
            .toList()       //Get list
            .toBlocking()   //Get list
            .single()       //Get list
            .stream()
            .map(Map.Entry::getValue)
            .collect(Collectors.toList());

        if (summaryInViews.isEmpty()) {
            System.out.println("## No more results.");
            return false;
        }

        AtomicInteger index = new AtomicInteger();
        summaryInViews.forEach(view -> System.out.println(
            "Page: " + pageNumber + ", item: " + index.getAndIncrement() + " [---]"));
        return true;
    }

    private ViewQuery viewQueryFor(String doctorNumber) {
        return ViewQuery.from(ORDER_DESIGN_DOC_NAME_5, ORDER_DESIGN_DOC_ALL_ORDERS_ORDERDATE_VIEW)
            .stale(Stale.FALSE)
            .descending(true)
            .reduce(false)
            .startKey(JsonArray.from(doctorNumber, JsonObject.empty()))
            .endKey(JsonArray.from(doctorNumber, null));
    }

    private Observable<AsyncViewRow> extractRowsOrError(AsyncViewResult viewResult) {
        if (viewResult.success()) {
            return viewResult.rows();
        } else {
            System.out.println("==> viewResult error! <===");
            return viewResult.error().flatMap(e -> Observable.error(new RuntimeException(e.toString())));
        }
    }

    private <T> Map.Entry<JsonArray, T> mapValueKeyPair(AsyncViewRow row, Class<T> clazz) {
        JsonArray key = (JsonArray) row.key();
        return Pair.of(key, null);
    }
}