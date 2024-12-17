package com.hust.smart_apartment.constants;

import lombok.experimental.UtilityClass;

import java.time.format.DateTimeFormatter;

/**
 * Application constants.
 */
@UtilityClass
public class AppConstants {

    public static final String DELIMITER = "$$$$$$$$$";

    // Regex for acceptable logins
    public static final String LOGIN_REGEX = "^[_.@A-Za-z0-9-]*$";

    public static final String SYSTEM_ACCOUNT = "system";

    public static final String DEFAULT_CLIENT_CODE = "system";

    public static final String VI_LANGUAGE = "vi-VN";

    public static final String US_LANGUAGE = "en-US";

    public static final String ANONYMOUS_USER = "anonymoususer";

    public static final int DEFAULT_PAGE = 0;

    public static final int DEFAULT_SIZE = 20;

    public static final String SESSION_EXPIRED = "Phiên đăng nhập đã hết hạn. Vui lòng đăng nhập lại!";

    public static final String UPDATE_APP_REQUIRED = "Đã có phiên bản cập nhật mới. Vui lòng cập nhật để tiếp tục sử dụng ứng dụng!";

    public static final String WEB_CODE = "CLIENT";

    public static final String ADMIN_APP_CODE = "ADMIN";

    public static final Integer CREATE = 1;

    public static final Integer UPDATE = 0;

    @UtilityClass
    public static class DateTimeFmt {

        public static final String YYYY_MM_DD_HH_MM_SS_ISO = "yyyy-MM-dd HH:mm:ss";
        public static final String YYYY_MM_DD_HH_MM_SS_ISO_T = "yyyy-MM-dd'T'HH:mm:ss";
        public static final String DD_MM_YYYY_SLASH = "dd/MM/yyyy";

        public static final String DD_MM_SLASH = "dd/MM";
        public static final String PATTERN_DATE_FILE_NAME = "yyyyMMdd";

        public static final String DD_MM_YYYY_HH_MM_SS_ISO = "dd/MM/yyyy HH:mm:ss";
        public static final String DD_MM_YYYY_HH_MM_ISO = "dd/MM/yyyy HH:mm";
        public static final String HH_MM_ISO = "HH:mm";

    }

    @UtilityClass
    public static class DateTimeFormatters {

        public static final DateTimeFormatter DD_MM_YYYY_SLASH_FORMATTER = DateTimeFormatter.ofPattern(DateTimeFmt.DD_MM_YYYY_SLASH);

        public static final DateTimeFormatter DD_MM_SLASH_FORMATTER = DateTimeFormatter.ofPattern(DateTimeFmt.DD_MM_SLASH);

        public static final DateTimeFormatter DD_MM_YYYY_HH_MM_SS_ISO_FORMATTER = DateTimeFormatter.ofPattern(DateTimeFmt.DD_MM_YYYY_HH_MM_SS_ISO);

    }

    @UtilityClass
    public static class ValidationUserField {
        public static final String PHONE_NUMBER_OPTIONAL_REGEX = "[0-9]{10,11}|^\\s*$";
        public static final String PHONE_NUMBER_REGEXP = "^[0-9]{10,11}";
        public static final String PASSPORT_REGEXP = "^[A-Za-z0-9]{1,10}$";
        public static final String EMAIL_REGEXP = "[a-zA-Z0-9][\\w-\\.]+@[a-zA-Z0-9][\\w-\\.]+(\\.[a-zA-Z0-9]+)$";
        public static final String CCCD_REGEXP = "^[0-9]{12}$";
        public static final String TAX_CODE_REGEXP = "^[0-9]{0,50}";
        public static final String NON_VIETNAMESE_CHARACTER = "^[^ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼẾỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\n]+";
        public static final String NON_SPACE = "\\S*";
        public static final String NON_SPECIAL_CHARACTER = "^[a-zA-Z0-9\\+]*$";
    }

    @UtilityClass
    public class CommonSymbol {

        public final String SPACE = " ";

        public final String DOT = ".";

        public final String BACKSLASH = "\\";

        public final String COMMA = ",";

        public final String DASH = "-";

        public final String SHIFT_DASH = "_";

        public final String COLON = ":";

        public final String FORWARD_SLASH = "/";
    }

    @UtilityClass
    public class ComparatorSymbol {

        public final String LT = "<";

        public final String LTE = "<=";

        public final String EQUAL = "=";

        public final String GT = ">";

        public final String GTE = ">=";
    }

    public static class ModelStatus {

        public static final Boolean ACTIVE = Boolean.TRUE;
        public static final Boolean INACTIVE = Boolean.FALSE;

        private ModelStatus() {
        }
    }

    public static class QueryParams {

        public static final String CLIENT_ID_PARAM = "clientId";
        public static final String USER_ID_PARAM = "userId";
        public static final String CONTROLLER_ID_PARAM = "controllerId";
        public static final String STATUS_PARAM = "status";
        public static final String LIMIT_PARAM = "limit";
        public static final String OFFSET_PARAM = "offset";
        public static final String FROM_DATE_PARAM = "fromDate";
        public static final String TO_DATE_PARAM = "toDate";
        public static final String DELETE_TIMESTAMP_PARAM = "deleteTimestamp";
        public static final String TERM_PARAM = "term";

        private QueryParams() {
        }

    }

    public static class InventoryStatus {
        public static final String DE_XUAT_KIEM_KE = "Đề xuất kiểm kê";
        public static final String DANG_THUC_HIEN = "Đang thực hiện";
        public static final String HUY = "Hủy";
        public static final String HOAN_THANH = "Hoàn thành";

        private InventoryStatus() {
        }
    }

    public static class InventoryAssetStatus {
        public static final String CHUA_KIEM_KE = "Chưa kiểm kê";
        public static final String DA_KIEM_KE = "Đã thực hiện";
        private InventoryAssetStatus() {
        }
    }

    public static class PermissionConstants {
        public static final String SELF = "SELF";
        public static final String BRANCH = "BRANCH";
        public static final String ORGANIZATION = "ORGANIZATION";

        private PermissionConstants() { }
    }

    public static class TicketStatus {
        public static final String CHO_PHAN_CONG = "WAIT_ASSIGN";
        public static final String DANG_THUC_HIEN = "DOING";
        public static final String CHO_XAC_NHAN_HOAN_THANH = "WAIT_COMPLETE_CONFIRM";
        public static final String CHO_XAC_NHAN_HUY = "WAIT_CANCEL_CONFIRM";
        public static final String TAM_DUNG = "PAUSE";
        public static final String DUNG_THUC_HIEN = "STOP";
        public static final String HOAN_THANH = "COMPLETE";
        public static final String HUY = "CANCEL";
        public static final String XAC_NHAN_HOAN_THANH = "APPROVE_COMPLETE_CONFIRM";
        public static final String TU_CHOI_HOAN_THANH = "REJECT_COMPLETE_CONFIRM";
        private TicketStatus() {
        }
    }

    public static class Operator {
        public static final String EQUAL = "=";
        public static final String NOT_EQUAL = "!=";
        public static final String LIKE = "contains";
        public static final String NOT_LIKE = "doesNotContain";
        public static final String NULL = "null";
        public static final String NOT_NULL = "notNull";
        public static final String IN = "in";
        public static final String NOT_IN = "notIn";
        private Operator() {
        }
    }

    public static class Combinator {
        public static final String AND = "and";
        public static final String OR = "or";
        private Combinator() {
        }
    }

    public static class AssetOwnerType {
        public static final Integer OWNER = 1;
        public static final Integer MANAGER = 2;
        private AssetOwnerType() {
        }
    }

    public static class AssetStatus {
        public static final String SOLD_OUT = "Đã thanh lý";
        private AssetStatus() {
        }
    }

    public static class TicketTransferStatus {
        public static final Integer WAIT_APPROVED = 1; // Đề xuất
        public static final Integer APPROVED = 2; // Đã xác nhận
        private TicketTransferStatus() {
        }
    }
}
