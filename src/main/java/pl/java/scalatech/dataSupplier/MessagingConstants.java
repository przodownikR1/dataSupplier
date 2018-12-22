package pl.java.scalatech.dataSupplier;

public final class MessagingConstants {

    public static final String EVENT_NAMESPACE = "pl.java.scalatech";

    public static final String EVENT_DOMAIN_VERSION = "0.1";
    public static final String EVENT_CREATED_DOMAIN = "CREATE_DOMAIN";
    public static final String EVENT_MODIFIED_DOMAIN = "MODIFIED_DOMAIN";
    public static final String EVENT_DELETED_DOMAIN = "DELETED_DOMAIN";

    public static final String OCCURED_ON = "OccuredOn";
    public static final String TYPE = "Type";

    private MessagingConstants() {
        throw new AssertionError();
    }
}
