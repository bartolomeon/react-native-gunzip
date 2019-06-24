
# react-native-gunzip

## Getting started

`$ npm install react-native-gunzip --save`

### Mostly automatic installation

`$ react-native link react-native-gunzip`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-gunzip` and add `RNGunzip.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNGunzip.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.reactlibrary.RNGunzipPackage;` to the imports at the top of the file
  - Add `new RNGunzipPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-gunzip'
  	project(':react-native-gunzip').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-gunzip/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-gunzip')
  	```


## Usage
```javascript
import RNGunzip from 'react-native-gunzip'

//won't overwrite destination (results in error):
RNGunzip.gunzip(sourceFilePath, destinationFilePath)

//overwrites the destination file if exists:
RNGunzip.gunzip(sourceFilePath, destinationFilePath, true)

```
  
