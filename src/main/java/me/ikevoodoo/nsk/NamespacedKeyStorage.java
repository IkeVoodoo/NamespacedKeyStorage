package me.ikevoodoo.nsk;

import org.bukkit.NamespacedKey;
import org.bukkit.plugin.Plugin;

import java.util.EnumMap;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

@SuppressWarnings("unused")
public final class NamespacedKeyStorage<T extends Enum<T>> implements Iterable<NamespacedKey> {

    private final Plugin plugin;
    private final EnumMap<T, NamespacedKey> keys;

    public NamespacedKeyStorage(Plugin plugin, Class<T> enumType) {
        this.plugin = plugin;
        this.keys = new EnumMap<>(enumType);
    }

    public NamespacedKey get(T key) {
        return this.keys.computeIfAbsent(key, k -> new NamespacedKey(this.plugin, k.name()));
    }

    public boolean contains(T key) {
        return this.keys.containsKey(key);
    }

    public int size() {
        return this.keys.size();
    }

    @Override
    public Iterator<NamespacedKey> iterator() {
        return this.keys.values().iterator();
    }

    @Override
    public void forEach(Consumer<? super NamespacedKey> action) {
        this.keys.forEach((k, v) -> action.accept(v));
    }

    @Override
    public Spliterator<NamespacedKey> spliterator() {
        return this.keys.values().spliterator();
    }
}
