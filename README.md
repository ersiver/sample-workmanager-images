# sample-workmanager-images
The app was built following <b>Google Developers Codelabs</b>.
The app blurs photos and images and saves the result to a file with use of WorkManager concepts. The app demonstrates following features:

+ Scheduling a OneOffWorkRequest

+ Input and Output parameters

+ Chaining work together WorkRequests

+ Naming Unique WorkRequest chains

+ Tagging WorkRequests

+ Displaying WorkInfo in the UI

+ Cancelling WorkRequests

+ Adding constraints to a WorkRequest

+ Custom configuration

+ Publishing progress from Worker

+ Test Workers

+ View Binding to interact with views in place of findViewById.

## CheatSheet
### What is the WorkManager:
+ It is a backwards <b>compatible, flexible and simple</b> library for deferrable <b>background work</b>.

+ It is the recommended task scheduler on Android for deferrable work, with a <b>guarantee to be executed</b>.

+ It is part of Android Jetpack and an Architecture Component for background work that needs a combination of opportunistic and guaranteed execution.<b>Opportunistic</b> execution means that WorkManager will do the background work as soon as it can. Guaranteed execution means that WorkManager will take care of the logic to start your work under a variety of situations, even if you navigate away from your app.

+ WorkManager sits on top of a few APIs such as <b>JobScheduler and AlarmManager</b>. WorkManager picks the right APIs to use, based on conditions like the user's device API level.

### WorkManager benefits:
+ Support for both asynchronous one-off and periodic tasks

+ Support for <b>constraints</b> such as network conditions, storage space, and charging status

+ <b>Chaining</b> of complex work requests, including running work in parallel

+ Output from one work request used as input for the next

+ Handles API level compatibility back to API level 14 

+ Works with or without Google Play services

+ Follows system health best practices

+ <b>LiveData</b> support to easily display work request state in UI

### When to use WorkManager:
+ The WorkManager library is a good choice for tasks that are useful to complete, even if the user navigates away from the particular screen or your app.

+ Some examples of tasks that are a good use of WorkManager:
<br>  a) Uploading logs
<br>  b) Applying filters to images and saving the image
<br>  c) Periodically syncing local data with the network

### Licence
Copyright 2020 Google LLC.        
  




