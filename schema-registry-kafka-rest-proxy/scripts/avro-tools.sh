# can be put in any directory
wget https://repo1.maven.org/maven2/org/apache/avro/avro-tools/1.12.0/avro-tools-1.12.0.jar

curl -O https://repo1.maven.org/maven2/org/apache/avro/avro-tools/1.12.0/avro-tools-1.12.0.jar


# run from project folder
java -jar avro-tools-1.12.0.jar toJson --pretty yourfile.avro

# get the schema
java -jar avro-tools-1.12.0.jar getschema yourfile.avro
