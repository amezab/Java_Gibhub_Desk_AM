//Blueprint of the locations
public class Location {
    public String name;
    public String firstVisitDes;
    public String repeatVisitDes;
    public boolean hasBeenVisited;

    //Constructor to build the object
    public Location(String name, String firstVisitDes, String repeatVisitDes) {
        this.name = name;
        this.firstVisitDes = firstVisitDes;
        this.repeatVisitDes = repeatVisitDes;
        this.hasBeenVisited = false;

    }

//This one checks if this is the first time visiting the place or else
        public String getDescription() {
            if (!hasBeenVisited) {
                return firstVisitDes;
            } else {
                return repeatVisitDes;
            }

        }

//This one changes the value of the boolean/update
        public void setHasBeenVisited ( boolean visited){
            this.hasBeenVisited = visited;
        }
    }

