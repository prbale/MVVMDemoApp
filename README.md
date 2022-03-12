"# MvvMDemoApp" 


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
