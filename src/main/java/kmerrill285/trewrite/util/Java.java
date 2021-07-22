package kmerrill285.trewrite.util;

import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.loading.FMLCommonLaunchHandler;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.Optional;
import java.util.function.Consumer;

public class Java {
    public static final PrintStream out = new PrintStream(new FileOutputStream(FileDescriptor.out));

    public static <T> Consumer<?> consume(Class<T> type, Consumer<T> handler) {
        return anything -> Cast.optionally(anything, type).ifPresent(handler);
    }

    public static long hashCode(Object... a) {
        if (a == null)
            return 0L;
        long result = 1L;
        for (Object element : a)
            result = 31L * result + ((element instanceof Long) ? ((Long)element).longValue() : ((element == null) ? 0L : element.hashCode()));
        return result;
    }

    public static Optional<Class<?>> getClass(String name) {
        try {
            return Optional.of(Class.forName(name));
        } catch (Throwable err) {
            return Optional.empty();
        }
    }

    public static Optional<Method> getDeclaredMethod(Optional<Class<?>> clazz, String method, Class<?>... params) {
        return clazz.map(c -> {
            try {
                Method m = c.getDeclaredMethod(method, params);
                m.setAccessible(true);
                return m;
            } catch (NoSuchMethodException|SecurityException e) {
                return null;
            }
        });
    }
}
