/*
 * Copyright (c) 2020 Couchbase, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.ClusterOptions;
import com.couchbase.client.java.Collection;
import com.couchbase.client.java.Scope;
import com.couchbase.client.java.env.ClusterEnvironment;

import java.time.Duration;

public class managing_connections {

	String connectionString = "localhost";
	String username = "Administrator";
	String password = "password";

	Cluster cluster;
	Bucket bucket;
	Scope scope;
	Collection collection;

	private void init() {
		// tag::connection_1[];
		ClusterEnvironment environment = ClusterEnvironment.builder().build();
		cluster = Cluster.connect(connectionString,
				ClusterOptions.clusterOptions(username, password).environment(environment));
		bucket = cluster.bucket("travel-sample");
		scope = bucket.scope("inventory");
		collection = scope.collection("airport"); // end::connection_1[]
	}

	public void managing_connections_5() throws Exception { // file: howtos/pages/managing-connections.adoc line: 125
		// tag::managing_connections_5[]
		int customKvPort = 1234; // default is 11210 (or 11207 for TLS)
		String connectionString = "127.0.0.1:" + customKvPort;
		Cluster cluster = Cluster.connect(connectionString, username, password);
		// end::managing_connections_5[]
	}

	public void managing_connections_6() throws Exception { // file: howtos/pages/managing-connections.adoc line: 125
		// tag::managing_connections_6[]
		int customManagerPort = 2345; // default is 8091 (or 18091 for TLS)
		String connectionString = "127.0.0.1:" + customManagerPort + "=manager";
		Cluster cluster = Cluster.connect(connectionString, username, password);
		// end::managing_connections_6[]
	}

	public void managing_connections_8() throws Exception { // file: howtos/pages/managing-connections.adoc line: 242
		// tag::managing_connections_8[]
		Cluster cluster = Cluster.connect("127.0.0.1", "Administrator", "password");
		cluster.waitUntilReady(Duration.ofSeconds(10));
		Bucket bucket = cluster.bucket("travel-sample");
		Collection collection = bucket.defaultCollection();
		// end::managing_connections_8[]
	}

	public void managing_connections_9() throws Exception { // file: howtos/pages/managing-connections.adoc line: 252
		// tag::managing_connections_9[]
		Cluster cluster = Cluster.connect("127.0.0.1", "Administrator", "password");
		cluster.waitUntilReady(Duration.ofSeconds(10));
		Bucket bucket = cluster.bucket("travel-sample");
		Collection collection = bucket.defaultCollection();
		// end::managing_connections_9[]
	}

	public static void main(String[] args) throws Exception {
		managing_connections obj = new managing_connections();
		obj.init();
		obj.managing_connections_5();
		obj.managing_connections_8();
		obj.managing_connections_9();
	}
}
