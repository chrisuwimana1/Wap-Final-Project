package task.model;

public enum UserEnum {
    ADMIN(1),PROJECTMANAGER(2),DEVELOPER(3);

    private int userRole;

    UserEnum(int userRole){
        this.userRole = userRole;
    }

    public int getUserRole() {
        return userRole;
    }
}
