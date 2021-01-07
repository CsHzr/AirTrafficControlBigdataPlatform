package metrics;

import io.prometheus.client.exporter.MetricsServlet;
import io.prometheus.client.hotspot.DefaultExports;
import io.prometheus.client.exporter.HTTPServer;
import io.prometheus.client.exporter.common.TextFormat;

import java.io.IOException;

import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;
import io.prometheus.client.Histogram;
import io.prometheus.client.Summary;

public class MyMetrics {
	
	static final Counter route_point_counter=Counter.build()
			.name("route_point_counter").help("route point counter")
			.labelNames("routepoint","count").register();
	
	static final Gauge route_point_gauge=Gauge.build()
			.name("route_point_gauge").help("route point gauge")
			.labelNames("routepoint","gauge").register();
	
	static final Histogram route_point_histogram=Histogram.build()
			.name("route_point_histogram").help("route point histogram")
			.labelNames("routepoint","histogram").register();
	
	static final Summary route_point_summary=Summary.build()
			.name("route_point_summary").help("route point summary")
			.labelNames("routepoint","summary").register();
	
	
	public static void Initialize() throws IOException {
		DefaultExports.initialize();
		new io.prometheus.client.exporter.HTTPServer(8088);
	}
	
	public static void CountOneRoutePoint() {
		if(route_point_counter==null) {
			System.out.println("NULL COUNTER!");
		}else {
			route_point_counter.inc();
		}
	}

}
