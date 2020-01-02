package team6072.robot2019.logging;

import java.util.ArrayList;

public class LogWrapper {

    private String mName;
    private FileType mFileType;
    private Permission mPermission;

    public enum FileType {
        COMMAND, COMMAND_GROUP, PID, SUBSYSTEM, WATCHDOG, ROBOT, CONTROLBOARD;
    }

    public enum Permission {
        PERIODIC_DEBUG_OFF, ALL, WARNINGS_AND_ERRORS, ERRORS_ONLY;
    }

    public LogWrapper(FileType fileType, String name, Permission permission) {
        mName = name;
        mFileType = fileType;
        
        mPermission = permission;
    }

    private int mIterations;
    public void periodicDebug(String s, int iterations){
        if(mPermission == Permission.ALL){
            if(mIterations % iterations == 0){
                System.out.print(mFileType.toString() + ": " + mName + ": " + s + "\n");
            }
            mIterations++;
        }
    }

    public void debug(String s) {
        if (mPermission == Permission.ALL || mPermission == Permission.PERIODIC_DEBUG_OFF) {
            System.out.print(mFileType.toString() + ": " + mName + ": " + s + "\n");
        }
    }

    public void alarm(String s) {
        if (mPermission == Permission.ALL || mPermission == Permission.PERIODIC_DEBUG_OFF) {
            System.out.print("**ALARM: " + mFileType.toString() + ": " + mName + ": " + s + "\n");
        }
    }

    public void warning(String s) {
        if(mPermission == Permission.ALL || mPermission == Permission.WARNINGS_AND_ERRORS || mPermission == Permission.PERIODIC_DEBUG_OFF){
            System.out.print("****************************************************************************\n" + "WARNING: "
                + mFileType.toString() + ": " + mName + ": " + s
                + "\n****************************************************************************" + "\n");

        }
    }

    public void error(String s) {
        System.out.print("****************************************************************************\n" + "ERROR: "
                + mFileType.toString() + ": " + mName + ": " + s
                + "\n****************************************************************************" + "\n");

    }
}