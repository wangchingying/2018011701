package com.cyw.a2018011701.data;

import android.content.Context;

/**
 * Created by Student on 2018/1/18.
 */

public class StudentDAOFactory {
    public static StudentDAO getDAOInstance(Context context, DBtype dbType)
    {
        switch (dbType)
        {
            case MEMORY:
                return new StudentScoreDAO();
            case FILE:
                return new StudentFileDAO(context);
            case DB:
                return new StudentDAODBImpl(context);
            case CLOUD:
                return new StudentCloudDAOImpl(context);
        }
        return null;
    }
}
