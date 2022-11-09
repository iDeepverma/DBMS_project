echo "Creating Database";
mysql -u root -h localhost <Total_tables.sql -p
echo "Building Successfull";
echo "Running"
java -jar target/*.jar;