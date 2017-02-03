package jtwirc.annotation;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class AnnotationSweeper
{

    public AnnotationSweeper()
    {

    }

    private static Class[] getClasses(String packageName) throws ClassNotFoundException, IOException
    {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<>();

        while (resources.hasMoreElements())
        {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }

        ArrayList<Class<? extends Object>> classes = new ArrayList<>();

        for (File directory : dirs)
        {
            classes.addAll(findClasses(directory, packageName));
        }
        return classes.toArray(new Class[classes.size()]);
    }

    private static List<Class<? extends Object>> findClasses(File directory, String packageName) throws ClassNotFoundException
    {
        List<Class<? extends Object>> classes = new ArrayList<>();
        if (!directory.exists())
        {
            return classes;
        }

        File[] files = directory.listFiles();
        assert files != null;

        for (File file : files)
        {
            if (file.isDirectory())
            {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            }
            else if (file.getName().endsWith(".class"))
            {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }

    public Class[] sweep(AnnotationType type) throws ClassNotFoundException, IOException
    {
        switch (type)
        {
            case DEBUG:
                return sweep(DebugLevel.class);
            case UNFINISHED:
                return sweep(Unfinished.class);
            default:
                return null;
        }
    }

    private Class[] sweep(Class<? extends java.lang.annotation.Annotation> annotation) throws ClassNotFoundException, IOException
    {
        List<Class> classes = new ArrayList<>();
        for (Class clazz : getClasses("jtwirc"))
        {
            if (clazz.isAnnotationPresent(annotation))
            {
                classes.add(clazz);
            }
        }

        return classes.toArray(new Class[classes.size()]);
    }

    public enum AnnotationType
    {
        DEBUG, UNFINISHED
    }
}
