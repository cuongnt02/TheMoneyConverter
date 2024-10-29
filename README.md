# THE MONEY CONVERTER
## Introduction
The money converter is an application using the Open Exchange Rates API to
convert money from one to another
## Installation
- Clone the repository
``` bash
git clone https://github.com/cuongnt02/TheMoneyConverter.git
cd TheMoneyConverter
```

- Connect to your device via adb
``` bash
adb pair [YOUR_IP]:[PORT] [PAIRING_CODE]
adb connect [YOUR_IP]:[PORT]
./gradlew installDebug
```

- Run the application on the device

## Project Structure
The project skeleton is based on some the MVVM structure
- The data package store models data, operations on data and apis
- The viewmodels package store viewmodel, which is observable reatime data that linked with the ui
- The ui package store ui data and operations that directly affects the user interface

## Challenges and Considerations
- The Logging is not verbose.
- Fetching data is slow and should be optimized by improving performance.
- A much better UI design for better user experience

## Demo
https://studio.youtube.com/channel/UCuQ9fYvtULle-LGS3CypsSg

