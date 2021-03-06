package com.jlmd.android.evernoteclient.app.di;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
import android.util.Log;
import com.googlecode.tesseract.android.TessBaseAPI;
import dagger.Module;
import dagger.Provides;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.inject.Named;
import javax.inject.Singleton;

/**
 * Tesseract module which provides tesseract api and copies tesseract languages to external storage
 *
 * @author jlmd
 */
@Module(
    complete = false,
    library = true)
public final class TesseractModule {

  private static final String LANG = "eng";

  /**
   * Copies tesseract languages into device external storage and returns path uri
   */
  @Singleton
  @Named("tesseract_lang_path")
  @Provides
  String provideTesseractLangPath(Context appContext) {
    String basePath =
        Environment.getExternalStorageDirectory().getAbsolutePath() + "/EvernoteClient/";
    String tessDataFolder = "tessdata/";
    String languageExtension = ".traineddata";
    String externalLangPath = basePath + tessDataFolder + LANG + languageExtension;
    File dir = new File(basePath + tessDataFolder);
    if (!dir.exists()) {
      dir.mkdirs();
    }
    if (!(new File(externalLangPath)).exists()) {
      String assetLangPath = tessDataFolder + LANG + languageExtension;
      try {
        copyFileFromAssetsToExternal(appContext, assetLangPath, externalLangPath);
      } catch (IOException e) {
        Log.i("ProvideTesseractLang", "Error copying files");
        return "";
      }
    }
    return basePath;
  }

  @Singleton
  @Provides
  TessBaseAPI provideTessBaseAPI(@Named("tesseract_lang_path") String tesseractLangPath) {
    TessBaseAPI tessBaseAPI = new TessBaseAPI();
    if (!"".equals(tesseractLangPath)) {
      tessBaseAPI.init(tesseractLangPath, LANG);
      return tessBaseAPI;
    } else {
      return null;
    }
  }

  private void copyFileFromAssetsToExternal(Context context, String assetPath, String externalPath)
      throws IOException {
    AssetManager assetManager = context.getAssets();
    InputStream in = assetManager.open(assetPath);
    OutputStream out = new FileOutputStream(externalPath);

    // Transfer bytes from in to out
    byte[] buf = new byte[1024];
    int len;
    while ((len = in.read(buf)) > 0) {
      out.write(buf, 0, len);
    }
    in.close();
    out.close();
  }
}
