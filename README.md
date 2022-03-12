# Android MVVM Architecture: Sample App

## Screenshots
<p align="center">
<img src="screenshots/deals_list.png" width="235" height="500"/>
<img src="screenshots/deal_details.png" width="235" height="500"/>
</p>


## Package Structures

```
com.bale.demoapplication # Root Package
├── constants            # Api and Application related constants
├── data                 # For data modeling layer
│   ├── model            # Data classes
│   └── repository       # Repository and dependent classes
|
├── dependencyinjection  # Dependency injection modules
│   ├── builder          # Activity and Fragment Builder Modules
│   ├── component        # Application Component
│   └── module           # Other Modules used for DI
|       └── viewmodel    # ViewModel Module and dependent classes
│
├── extension            # Kotlin extensions
|
├── listeners            # Listeners
|
├── ui                   # Fragment / View layer
│   ├── base             # Base Classes for Activity and Fragment
│   ├── dealDetails      # Detail screen Fragment and ViewModel
│   ├── dealList         # List screen Fragment and ViewModel
│   ├── profile          # Profile screen Fragment and ViewModel
│   └── MainActivity     # Parent Activity
|
└── DealsApplication     # Application Class

```


Just make pull request. You are in!