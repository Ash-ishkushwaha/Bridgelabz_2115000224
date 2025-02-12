class Movie {
    private String title;
    private String director;
    private int yearOfRelease;
    private double rating;
    private Movie next;
    private Movie prev;

    public Movie(String title, String director, int yearOfRelease, double rating) {
        this.title = title;
        this.director = director;
        this.yearOfRelease = yearOfRelease;
        this.rating = rating;
        this.next = null;
        this.prev = null;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Movie getNext() {
        return next;
    }

    public void setNext(Movie next) {
        this.next = next;
    }

    public Movie getPrev() {
        return prev;
    }

    public void setPrev(Movie prev) {
        this.prev = prev;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Director: " + director + ", Year of Release: " + yearOfRelease + ", Rating: " + rating;
    }
}

public class MovieManagementSystem {
    private Movie head;
    private Movie tail;

    public MovieManagementSystem() {
        this.head = null;
        this.tail = null;
    }

    public void addMovieAtBeginning(String title, String director, int yearOfRelease, double rating) {
        Movie newMovie = new Movie(title, director, yearOfRelease, rating);
        if (head == null) {
            head = tail = newMovie;
        } else {
            newMovie.setNext(head);
            head.setPrev(newMovie);
            head = newMovie;
        }
    }

    public void addMovieAtEnd(String title, String director, int yearOfRelease, double rating) {
        Movie newMovie = new Movie(title, director, yearOfRelease, rating);
        if (tail == null) {
            head = tail = newMovie;
        } else {
            newMovie.setPrev(tail);
            tail.setNext(newMovie);
            tail = newMovie;
        }
    }

    public void addMovieAtPosition(int position, String title, String director, int yearOfRelease, double rating) {
        if (position <= 0) {
            addMovieAtBeginning(title, director, yearOfRelease, rating);
            return;
        }
        Movie newMovie = new Movie(title, director, yearOfRelease, rating);
        Movie current = head;
        for (int i = 1; i < position && current != null; i++) {
            current = current.getNext();
        }
        if (current != null) {
            newMovie.setNext(current.getNext());
            newMovie.setPrev(current);
            if (current.getNext() != null) {
                current.getNext().setPrev(newMovie);
            } else {
                tail = newMovie;
            }
            current.setNext(newMovie);
        }
    }

    public void removeMovieByTitle(String title) {
        if (head == null) return;
        if (head.getTitle().equals(title)) {
            head = head.getNext();
            if (head != null) {
                head.setPrev(null);
            } else {
                tail = null;
            }
            return;
        }
        Movie current = head;
        while (current != null && !current.getTitle().equals(title)) {
            current = current.getNext();
        }
        if (current != null) {
            if (current.getNext() != null) {
                current.getNext().setPrev(current.getPrev());
            } else {
                tail = current.getPrev();
            }
            if (current.getPrev() != null) {
                current.getPrev().setNext(current.getNext());
            }
        }
    }

    public Movie searchMovieByDirector(String director) {
        Movie current = head;
        while (current != null) {
            if (current.getDirector().equals(director)) {
                return current;
            }
            current = current.getNext();
        }
        return null;
    }

    public Movie searchMovieByRating(double rating) {
        Movie current = head;
        while (current != null) {
            if (current.getRating() == rating) {
                return current;
            }
            current = current.getNext();
        }
        return null;
    }

    public void displayAllMovies() {
        Movie current = head;
        while (current != null) {
            System.out.println(current);
            current = current.getNext();
        }
    }

    public void displayAllMoviesInReverse() {
        Movie current = tail;
        while (current != null) {
            System.out.println(current);
            current = current.getPrev();
        }
    }

    public void updateMovieRating(String title, double newRating) {
        Movie movie = searchMovieByTitle(title);
        if (movie != null) {
            movie.setRating(newRating);
        }
    }

    private Movie searchMovieByTitle(String title) {
        Movie current = head;
        while (current != null) {
            if (current.getTitle().equals(title)) {
                return current;
            }
            current = current.getNext();
        }
        return null;
    }

    public static void main(String[] args) {
        MovieManagementSystem manager = new MovieManagementSystem();
        manager.addMovieAtBeginning("Inception", "Christopher Nolan", 2010, 8.8);
        manager.addMovieAtEnd("The Dark Knight", "Christopher Nolan", 2008, 9.0);
        manager.addMovieAtPosition(1, "Interstellar", "Christopher Nolan", 2014, 8.6);

        System.out.println("Displaying all movies:");
        manager.displayAllMovies();
        System.out.println();
        System.out.println("Displaying all movies in reverse:");
        manager.displayAllMoviesInReverse();
        System.out.println();
        
        System.out.println("Searching for movies directed by 'Christopher Nolan':");
        Movie searchResult = manager.searchMovieByDirector("Christopher Nolan");
        if (searchResult != null) {
            System.out.println(searchResult);
        } else {
            System.out.println("Movie not found.");
        }
        System.out.println();
        System.out.println("Updating rating for 'Inception':");
        manager.updateMovieRating("Inception", 9.0);
       
        System.out.println("Removing 'The Dark Knight':");
        manager.removeMovieByTitle("The Dark Knight");
        
        System.out.println();
        manager.displayAllMovies();
    }
}