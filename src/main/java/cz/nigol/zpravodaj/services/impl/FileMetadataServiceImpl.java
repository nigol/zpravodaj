package cz.nigol.zpravodaj.services.impl;

import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import cz.nigol.zpravodaj.entities.FileMetadata;
import cz.nigol.zpravodaj.entities.User;
import cz.nigol.zpravodaj.exceptions.UploadFailedException;
import cz.nigol.zpravodaj.services.FileMetadataService;

import static java.nio.file.StandardOpenOption.*;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;

@Stateless
public class FileMetadataServiceImpl implements FileMetadataService {
    @PersistenceContext(unitName="zpravodajPU")
    private EntityManager em;

    @Override
    public List<FileMetadata> getByUser(User user) {
	TypedQuery<FileMetadata> typedQuery = em.createNamedQuery(FileMetadata.GET_BY_USER, FileMetadata.class);
	typedQuery.setParameter(FileMetadata.USER_PARAM, user);
	return new ArrayList<>(typedQuery.getResultList());
    }

    @Override
    public FileMetadata save(FileMetadata fileMetadata, String path, byte[] bytes) throws UploadFailedException {
	FileMetadata result = em.merge(fileMetadata);
	Set<OpenOption> options = new HashSet<OpenOption>();
	options.add(APPEND);
	options.add(CREATE);
	Set<PosixFilePermission> perms =
	    PosixFilePermissions.fromString("rw-r-----");
	FileAttribute<Set<PosixFilePermission>> attr =
	    PosixFilePermissions.asFileAttribute(perms);
	Path file = Paths.get(path + fileMetadata.getPath());
	ByteBuffer bb = ByteBuffer.wrap(bytes);
	try {
	    Files.createDirectories(file.getParent());
	    Files.createFile(file);
	    SeekableByteChannel sbc = Files.newByteChannel(file, options, attr);
	    sbc.write(bb);
	} catch (IOException e) {
	    throw new UploadFailedException(e);
	}
	return result;
    }
}
