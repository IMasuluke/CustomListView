package planner.jsonparser;

/**
 * Created by tumi on 2017/07/07.
 */

public class VideoList {

    private String Name,Size,Description,Lecturer;

    public VideoList(String Name, String Size,String Description,String Lecturer){
        this.setName(Name);
        this.setSize(Size);
        this.setDescription(Description);
        this.setLecturer(Lecturer);
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String size) {
        Size = size;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getLecturer() {
        return Lecturer;
    }

    public void setLecturer(String lecturer) {
        Lecturer = lecturer;
    }
}
