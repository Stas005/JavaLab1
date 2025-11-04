package repositories;

import java.util.*;
import java.util.logging.Logger;

public class GenericRepository<T> {
    private static final Logger logger = Logger.getLogger(GenericRepository.class.getName());
    private final Map<String, T> items;
    private final IdentityExtractor<T> identityExtractor;

    public GenericRepository(IdentityExtractor<T> identityExtractor) {
        this.identityExtractor = identityExtractor;
        this.items = new HashMap<>();
        logger.info("GenericRepository initialized with IdentityExtractor");
    }

    public void add(T item) {
        if (item == null) {
            logger.warning("Try to add null item to repository");
            return;
        }

        String identity = identityExtractor.extractIdentity(item);
        if (identity == null || identity.trim().isEmpty()) {
            logger.warning("Try to add item with null or empty identity");
            return;
        }

        if (items.containsKey(identity)) {
            logger.warning("Duplicate: Item with identity " + identity + "  exists. Duplicate not added.");
            return;
        }

        items.put(identity, item);
        logger.info("Added item with identity: " + identity);
    }

    public boolean delete(String identity) {
        if (identity == null || identity.trim().isEmpty()) {
            logger.warning("Try to delete item with null or empty identity");
            return false;
        }

        T removed = items.remove(identity);
        if (removed != null) {
            logger.info("Deleted item with identity: " + identity);
            return true;
        } else {
            logger.info("No item found with identity: " + identity + ". Nothing deleted.");
            return false;
        }
    }

    public List<T> getAll() {
        List<T> allItems = new ArrayList<>(items.values());
        logger.info("Get all items. Total count: " + allItems.size());
        return allItems;
    }

    public T findByIdentity(String identity) {
        if (identity == null || identity.trim().isEmpty()) {
            logger.warning("Try to find item with null or empty identity");
            return null;
        }

        T item = items.get(identity);
        return item;
    }
}
