/** Secondary entity **/
enum Genre {
    ACTION("Action"),
    BIOGRAPHY("Biography"),
    COMEDY("Comedy"),
    CRIME("Crime"),
    DOCUMENTARY("Documentary"),
    DRAMA("Drama"),
    FAMILY("Family"),
    HISTORY("History"),
    MYSTERY("Mystery"),
    ROMANCE("Romance"),
    THRILLER("Thriller"),
    WAR("War");

    private final String genreName;


    Genre(String genreName) {
        this.genreName = genreName;
    }


    @Override
    public String toString() {
        return genreName;
    }
}
