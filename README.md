wikipedia_pagerank
==================

Environment: (IBM Biginsights installation on a VM Image - Linux redhat run through VMWare fusion)

Steps to run 

- run it for a smaller dataset to check performance
head -5000 wikipedia.tsv > wikid.tsv
file format - tab separated file of format:
date articlename recordtype text
record type can be article,title,user and so on. we are only interested in records of type article

- copy data file to hdfs
start all biginsights services - this starts all the nodes (namenode,datanode,jobclient,jobtracker)

hadoop fs -mkdir test
hadoop fs -copyfromLocal wikid.tsv /user/biadmin/test/wikid.tsv  
hadoop fs -ls test

- execute first jar to create link graph
export project wikipediapagerank into a extractlinks.jar file
hadoop jar extactlinks.jar pagerank.mapreduce_driver /user/biadmin/test/wikid.tsv /user/biadmin/testresults/

for rerun , remove directory using remove recursive
hadoop fs -rmr /user/biadmin/testresults


- execute second jar to setup pagerank weights for outbound links
hadoop jar calpr1.jar pagerank1.driver_calculatepagerank /user/biadmin/testresults/ /user/biadmin/testresults1


- execute third jar to calculate pagerank score for each page and output linkgraph
hadoop jar calpr2.jar pagerank1.driver_calculatepagerank /user/biadmin/testresults1/ /user/biadmin/testresults2
 
- Repeat previous two steps iteratively for a number of times.
