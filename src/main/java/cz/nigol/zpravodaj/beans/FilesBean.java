package cz.nigol.zpravodaj.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.logging.Log;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import cz.nigol.zpravodaj.entities.FileMetadata;
import cz.nigol.zpravodaj.entities.User;
import cz.nigol.zpravodaj.exceptions.UploadFailedException;
import cz.nigol.zpravodaj.qualifiers.LoggedUser;
import cz.nigol.zpravodaj.qualifiers.PathToWebapp;
import cz.nigol.zpravodaj.services.FileMetadataService;
import cz.nigol.zpravodaj.services.UserService;

@Named
@ViewScoped
public class FilesBean implements Serializable {
    private static final long serialVersionUID = -8530001074117001306L;
    @Inject
    private FileMetadataService fileMetadataService;
    @Inject
    @LoggedUser
    private User user;
    @Inject
    private UserService userService;
    @Inject
    private FacesContext facesContext;
    @Inject
    private Log log;
    @Inject
    @PathToWebapp
    private String path;
    private List<FileMetadata> files;
    private FileMetadata file;

    @PostConstruct
    public void init() {
	user = userService.getUserById(user.getId());
	files = fileMetadataService.getByUser(user);
    }

    public String endOfPath(String path) {
	return path.substring(path.lastIndexOf('/') + 1, path.length()).split("\\d{8,}")[1];
    }

    public void newFile() {
	file = new FileMetadata();
    }

    public void handleUpload(FileUploadEvent event) {
	file.setCreatedAt(new Date());
	file.setUser(user);
	UploadedFile uploadedFile = event.getFile();
	Date date = new Date();
	file.setPath("/upload/" + date.getTime() + uploadedFile.getFileName());
	try {
	    file = fileMetadataService.save(file, path, uploadedFile.getContents());
	} catch (UploadFailedException e) {
	    log.error(file.getPath(), e);
	    facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
							   "Chyba", "Chyba při nahrávání souboru."));
	}
	file = null;
	init();
	facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Uloženo",  "Soubor byl uložen."));
    }

    /**
     * @return the files
     */
    public List<FileMetadata> getFiles() {
	return files;
    }

    /**
     * @param files the files to set
     */
    public void setFiles(List<FileMetadata> files) {
	this.files = files;
    }

    /**
     * @return the file
     */
    public FileMetadata getFile() {
	return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(FileMetadata file) {
	this.file = file;
    }
}
