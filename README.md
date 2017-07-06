# RoomPersistenceLibrary

Room provides an abstraction layer over SQLite to allow fluent database access while harnessing the full power of SQLite.

# Room with ViewModel, LiveData,rxjava and retrolambda
this example will show you how to create, insert and retrieve data from Database using Room.
* And how we can retrieve data from Database Using Rxjava and ViewModel.
* How LiveData along with ViewModel to get data fast and use Own seprate LifeCycle.
* How ViewModel help us to get rid of problems like re-fetch data on config change, these UI controllers (activities, fragments, and so on) frequently need to make some asynchronous calls which may take some time to return.. The UI controller needs to manage these calls and clean them up when it is destroyed to avoid potential memory leaks. This requires a lot of maintenance,

# Benifits

*Avoid memory leaks.

*we don't have to save objects when orentation changes.


*don't load data again and on every recreate. 


*Like Loders its not attached with Activity or fragments. Its attached with the Application lifecycle.


*Help to minimize resources reuse.
