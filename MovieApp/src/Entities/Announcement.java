package Entity;

public class Announcement {

    private int announcementID;
    private static int announcementCounter = 1000;
    private Date creationDate;
    private Date releaseDate;
    private Movie associatedMovie;

    // Constructor with auto-generated ID
    public Announcement(Date creation, Date release, Movie movie) {
        announcementID = announcementCounter++;
        creationDate = creation;
        releaseDate = release;
        associatedMovie = movie;
        movie.setAnnouncement(this);
    }

    // Constructor with custom ID
    public Announcement(int id, Date creation, Date release, Movie movie) {
        announcementID = id;
        if (id > announcementCounter) {
            announcementCounter = id + 1;
        } else {
            announcementCounter++;
        }
        creationDate = creation;
        releaseDate = release;
        associatedMovie = movie;
        movie.setAnnouncement(this);
    }

    // Method to check if the announcement is scheduled
    public boolean isScheduled() {
        return (!creationDate.isBeforeCurrentDate() && releaseDate.isBeforeCurrentDate());
    }

    // Method to check if the announcement is live
    public boolean isLive() {
        return !releaseDate.isBeforeCurrentDate();
    }

    // Getters and Setters
    public int getAnnouncementID() {
        return announcementID;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Movie getAssociatedMovie() {
        return associatedMovie;
    }

    public void setAssociatedMovie(Movie associatedMovie) {
        this.associatedMovie = associatedMovie;
    }
}
