package com.reactlibrary;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;

import org.apache.commons.io.FileUtils;
import org.apache.commons.compress.utils.IOUtils;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipException;
import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;


public class RNGunzipModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;

  public RNGunzipModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "RNGunzip";
  }

  @ReactMethod
  public void gunzip(String source, String dest, Boolean force, Promise promise) {
    File sourceFile = new File(source);
    if (!sourceFile.exists()) {
      promise.reject("-2", "file not found");
      return;
    }

    File destFile = new File(dest);
    if (destFile.exists()) {
      if (!force) {
        promise.reject("-2", "destination file already exists - use force option to overwrite");
        return;
      }

      try {
        destFile.delete();
      } catch (IOException ex) {
        promise.reject("-2", "could not delete old destination file", ex);
      }
    }

    try (GZIPInputStream gzipInputStream = new GZIPInputStream(new FileInputStream(sourceFile));
        FileOutputStream outputStream = FileUtils.openOutputStream(destFile) )
    {
      IOUtils.copy(gzipInputStream, outputStream);

      WritableMap map = Arguments.createMap();
      map.putString("path", destFile.getAbsolutePath());
      promise.resolve(map);

    } catch (IOException e) {
      promise.reject("-2", e);
    } catch (ZipException e) {
      promise.reject("-2", "unable to open archive", e);
    } catch ( IllegalArgumentException e) {
      promise.reject("-2", "empty input file", e);
    }
  }
