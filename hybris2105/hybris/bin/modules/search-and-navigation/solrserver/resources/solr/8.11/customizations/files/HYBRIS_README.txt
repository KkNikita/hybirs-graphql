
hybris Changes
=============================

This is a modified version of Solr.

The following files/directories were removed:
- docs
- example
- dist/test-framework
- dist/solr-test-framework-8.11.1.jar
- server/solr-webapp/webapp/WEB-INF/lib/htrace-core4-4.1.0-incubating.jar

The following files/directories were added:
- HYBRIS_README.txt
- contrib/hybris
- server/solr/security.json.example
- server/solr/solr.p12
- server/solr/solr_client.p12

The following files/directories were modified/replaced:
- bin/solr.cmd:
	- workaround for https://issues.apache.org/jira/browse/SOLR-7283 (lines 19-20)
- bin/solr.in.cmd:
	- authentication and ssl configuration example (lines 217-236)
	- remove UseLargePages parameter from GC_TUNE parameters due to problems with running solr within docker container (line 238)
- bin/solr.in.sh:
	- authentication and ssl configuration example (lines 258-277)
	- remove UseLargePages parameter from GC_TUNE parameters due to problems with running solr within docker container (line 279)
- server/solr/solr.xml
- server/solr/configsets
- guava library (due to https://nvd.nist.gov/vuln/detail/CVE-2020-8908)
    - licenses/guava-25.1-jre.jar.sha1 -> licenses/guava-30.0-jre.jar.sha1
    - server/solr-webapp/webapp/WEB-INF/lib/guava-25.1-jre.jar -> server/solr-webapp/webapp/WEB-INF/lib/guava-30.0-jre.jar
- failureaccess-1.0.1 (due to https://issues.apache.org/jira/browse/SOLR-15090 which will be fixed for solr 9.0.0)
		- added licenses/failureaccess-1.0.1.jar.sha1
		- added licenses/failureaccess-LICENSE-ASL.txt
		- added licenses/licenses/failureaccess-NOTICE.txt
		- added server/solr-webapp/webapp/WEB-INF/lib/failureaccess-1.0.1.jar
- jackson-databind (due to https://nvd.nist.gov/vuln/detail/CVE-2020-36518)
		- licenses/jackson-databind-2.12.3.jar.sha1 -> licenses/jackson-databind-2.13.0.jar.sha1
		- contrib/prometheus-exporter/lib/jackson-databind-2.12.3.jar -> contrib/prometheus-exporter/lib/jackson-databind-2.13.0.jar
		- server/solr-webapp/webapp/WEB-INF/lib/jackson-databind-2.12.3.jar -> server/solr-webapp/webapp/WEB-INF/lib/jackson-databind-2.13.0.jar
- log4j libraries (due to https://nvd.nist.gov/vuln/detail/CVE-2021-44228, https://nvd.nist.gov/vuln/detail/CVE-2021-45046, https://nvd.nist.gov/vuln/detail/CVE-2021-45105, https://nvd.nist.gov/vuln/detail/CVE-2021-44832)
		- licenses/log4j-1.2-api-2.16.0.jar.sha1 -> licenses/log4j-1.2-api-2.17.1.jar.sha1
		- server/lib/ext/log4j-1.2-api-2.16.0.jar -> server/lib/ext/log4j-1.2-api-2.17.1.jar
		- licenses/log4j-api-2.16.0.jar.sha1 -> licenses/log4j-api-2.17.1.jar.sha1
		- server/lib/ext/log4j-api-2.16.0.jar -> server/lib/ext/log4j-api-2.17.1.jar
		- contrib/prometheus-exporter/lib/log4j-api-2.16.0.jar -> contrib/prometheus-exporter/lib/log4j-api-2.17.1.jar
		- licenses/log4j-core-2.16.0.jar.sha1 -> licenses/log4j-core-2.17.1.jar.sha1
		- server/lib/ext/log4j-core-2.16.0.jar -> server/lib/ext/log4j-core-2.17.1.jar
		- contrib/prometheus-exporter/lib/log4j-core-2.16.0.jar -> contrib/prometheus-exporter/lib/log4j-core-2.17.1.jar
		- licenses/log4j-layout-template-json-2.16.0.jar.sha1 -> licenses/log4j-layout-template-json-2.17.1.jar.sha1
		- server/lib/ext/log4j-layout-template-json-2.16.0.jar -> server/lib/ext/log4j-layout-template-json-2.17.1.jar
		- licenses/log4j-slf4j-impl-2.16.0.jar.sha1 -> licenses/log4j-slf4j-impl-2.17.1.jar.sha1
		- server/lib/ext/log4j-slf4j-impl-2.16.0.jar -> server/lib/ext/log4j-slf4j-impl-2.17.1.jar
		- contrib/prometheus-exporter/lib/log4j-slf4j-impl-2.16.0.jar -> contrib/prometheus-exporter/lib/log4j-slf4j-impl-2.17.1.jar
		- licenses/log4j-web-2.16.0.jar.sha1 -> licenses/log4j-web-2.17.1.jar.sha1
		- server/lib/ext/log4j-web-2.16.0.jar -> server/lib/ext/log4j-web-2.17.1.jar
- netty libraries (due to https://nvd.nist.gov/vuln/detail/CVE-2021-43797)
		- licenses/netty-all-4.1.68.Final.jar.sha1 -> licenses/netty-all-4.1.71.Final.jar.sha1
		- licenses/netty-buffer-4.1.68.Final.jar.sha1 -> licenses/netty-buffer-4.1.71.Final.jar.sha1
		- dist/solrj-lib/netty-buffer-4.1.68.Final.jar -> dist/solrj-lib/netty-buffer-4.1.71.Final.jar
		- server/solr-webapp/webapp/WEB-INF/lib/netty-buffer-4.1.68.Final.jar -> server/solr-webapp/webapp/WEB-INF/lib/netty-buffer-4.1.71.Final.jar
		- licenses/netty-codec-4.1.68.Final.jar.sha1 -> licenses/netty-codec-4.1.71.Final.jar.sha1
		- dist/solrj-lib/netty-codec-4.1.68.Final.jar -> dist/solrj-lib/netty-codec-4.1.71.Final.jar
		- server/solr-webapp/webapp/WEB-INF/lib/netty-codec-4.1.68.Final.jar -> server/solr-webapp/webapp/WEB-INF/lib/netty-codec-4.1.71.Final.jar
		- licenses/netty-common-4.1.68.Final.jar.sha1 -> licenses/netty-common-4.1.71.Final.jar.sha1
		- dist/solrj-lib/netty-common-4.1.68.Final.jar -> dist/solrj-lib/netty-common-4.1.71.Final.jar
		- server/solr-webapp/webapp/WEB-INF/lib/netty-common-4.1.68.Final.jar -> server/solr-webapp/webapp/WEB-INF/lib/netty-common-4.1.71.Final.jar
		- licenses/netty-handler-4.1.68.Final.jar.sha1 -> licenses/netty-handler-4.1.71.Final.jar.sha1
		- dist/solrj-lib/netty-handler-4.1.68.Final.jar -> dist/solrj-lib/netty-handler-4.1.71.Final.jar
		- server/solr-webapp/webapp/WEB-INF/lib/netty-handler-4.1.68.Final.jar -> server/solr-webapp/webapp/WEB-INF/lib/netty-handler-4.1.71.Final.jar
		- licenses/netty-resolver-4.1.68.Final.jar.sha1 -> licenses/netty-resolver-4.1.71.Final.jar.sha1
		- dist/solrj-lib/netty-resolver-4.1.68.Final.jar -> dist/solrj-lib/netty-resolver-4.1.71.Final.jar
		- server/solr-webapp/webapp/WEB-INF/lib/netty-resolver-4.1.68.Final.jar -> server/solr-webapp/webapp/WEB-INF/lib/netty-resolver-4.1.71.Final.jar
		- licenses/netty-transport-4.1.68.Final.jar.sha1 -> licenses/netty-transport-4.1.71.Final.jar.sha1
		- dist/solrj-lib/netty-transport-4.1.68.Final.jar -> dist/solrj-lib/netty-transport-4.1.71.Final.jar
		- server/solr-webapp/webapp/WEB-INF/lib/netty-transport-4.1.68.Final.jar -> server/solr-webapp/webapp/WEB-INF/lib/netty-transport-4.1.71.Final.jar
		- licenses/netty-transport-native-epoll-4.1.68.Final.jar.sha1 -> licenses/netty-transport-native-epoll-4.1.71.Final.jar.sha1
		- dist/solrj-lib/netty-transport-native-epoll-4.1.68.Final.jar -> dist/solrj-lib/netty-transport-native-epoll-4.1.71.Final.jar
		- server/solr-webapp/webapp/WEB-INF/lib/netty-transport-native-epoll-4.1.68.Final.jar -> server/solr-webapp/webapp/WEB-INF/lib/netty-transport-native-epoll-4.1.71.Final.jar
		- licenses/netty-transport-native-unix-common-4.1.68.Final.jar.sha1 -> licenses/netty-transport-native-unix-common-4.1.71.Final.jar.sha1
		- dist/solrj-lib/netty-transport-native-unix-common-4.1.68.Final.jar -> dist/solrj-lib/netty-transport-native-unix-common-4.1.71.Final.jar
		- server/solr-webapp/webapp/WEB-INF/lib/netty-transport-native-unix-common-4.1.68.Final.jar -> server/solr-webapp/webapp/WEB-INF/lib/netty-transport-native-unix-common-4.1.71.Final.jar
- protobuf libraries due to (due to https://nvd.nist.gov/vuln/detail/CVE-2021-22570 and https://nvd.nist.gov/vuln/detail/CVE-2021-22569)
		- licenses/protobuf-java-3.11.0.jar.sha1 -> licenses/protobuf-java-3.19.2.jar.sha1
		- contrib/gcs-repository/lib/protobuf-java-3.11.0.jar -> contrib/gcs-repository/lib/protobuf-java-3.19.2.jar
		- server/solr-webapp/webapp/WEB-INF/lib/protobuf-java-3.11.0.jar -> server/solr-webapp/webapp/WEB-INF/lib/protobuf-java-3.19.2.jar
		- licenses/protobuf-java-util-3.11.0.jar.sha1 -> licenses/protobuf-java-util-3.19.2.jar.sha1
		- contrib/gcs-repository/lib/protobuf-java-util-3.11.0.jar -> contrib/gcs-repository/lib/protobuf-java-util-3.19.2.jar
- zookeeper libraries (due to https://issues.apache.org/jira/browse/ZOOKEEPER-3822)
    - licenses/zookeeper-3.6.2.jar.sha1 -> licenses/zookeeper-3.5.9.jar.sha1
    - licenses/zookeeper-jute-3.6.2.jar.sha1 -> licenses/zookeeper-jute-3.5.9.jar.sha1
    - dist/solrj-lib/zookeeper-3.6.2.jar -> dist/solrj-lib/zookeeper-3.5.9.jar
    - dist/solrj-lib/zookeeper-jute-3.6.2.jar -> dist/solrj-lib/zookeeper-jute-3.5.9.jar
    - server/solr-webapp/webapp/WEB-INF/lib/zookeeper-3.6.2.jar -> server/solr-webapp/webapp/WEB-INF/lib/zookeeper-3.5.9.jar
    - server/solr-webapp/webapp/WEB-INF/lib/zookeeper-jute-3.6.2.jar -> server/solr-webapp/webapp/WEB-INF/lib/zookeeper-jute-3.5.9.jar
