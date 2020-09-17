# location-service
PoC on apache-ignite

Steps to run  **location-service**:

1. Install Apache-ignite 2.8.1 from : https://ignite.apache.org/download.cgi#maven
2. Go to the bin folder of Ignite installation directory `{ignite}/bin` from the command shell and start an Ignite-node: `./ignite.sh` 
3. In a text editor, open the `{ignite}/examples/sql/world.sql` file.
4. Update the **VALUE_TYPE** of `CREATE TABLE Country` to: `com.ignitepoc.location.model.Country`
5. Update the **VALUE_TYPE** and **KEY_TYPE** of `CREATE TABLE City` to: `com.ignitepoc.location.model.City` & `com.ignitepoc.location.model.CityKey` respectively. 
6. Open another command-line window, and go to the `{ignite}/bin` folder. Use SQLLine to connect to the cluster using this command: `./sqlline.sh -u jdbc:ignite:thin://127.0.0.1/ `
7. Load the sample database using this command: `!run ../examples/sql/world.sql`
8. After the database is created, quit SQLLine: `!q`
9. Import and run `location-service` in IntelliJ