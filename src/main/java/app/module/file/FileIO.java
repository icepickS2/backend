package app.module.file;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FileIO {
  public Map<String, Object> upload(MultipartFile file, String path);

  public Map<String, Object> info(String location);

  public Map<String, Object> delete(String location);

  public ResponseEntity<byte[]> download(String location);

}