This is an advanced SpringMVC demo with multi modules

##### RUN #######
$ mvn clean package -Pdev -Dmaven.test.skip=true tomcat7:run

###### CONTENT #########
1. redisTemplate
2. sessionTemplate
3. -P profile
4. Dubbo remote service call from project "DubboProvider" in same workspace, both multicast and local zookeeper tested
5. Interceptor
6. JUnit/TestNG to test Controller locally with MockMvc
7. slf4j-log4j, slf4j-logback