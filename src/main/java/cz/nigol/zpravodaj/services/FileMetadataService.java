package cz.nigol.zpravodaj.services;

import java.util.List;

import cz.nigol.zpravodaj.entities.FileMetadata;
import cz.nigol.zpravodaj.entities.User;
import cz.nigol.zpravodaj.exceptions.UploadFailedException;

public interface FileMetadataService {
    List<FileMetadata> getByUser(User user);
    FileMetadata save(FileMetadata fileMetadata, String path, byte[] bytes) throws UploadFailedException;
}
