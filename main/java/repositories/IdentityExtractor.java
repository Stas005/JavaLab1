package repositories;

@FunctionalInterface
public interface IdentityExtractor<T> {
    String extractIdentity(T object);
}
