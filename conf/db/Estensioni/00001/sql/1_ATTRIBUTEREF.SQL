 
# Riferimento: DatabaseVendor
    # ATTRIBUTE_REF
    INSERT INTO <SCH>ATTRIBUTE_REF (ATTRIBUTE_REF, DESCRIPTION, TYPE_ID, TYPE_SIZE, TYPE_SCALE, DB_SIZE, DB_SCALE, FW_DATA_OWNER_ID, USER_ID, TIMESTAMP, ONLY_ICON_ON_WEBGRIDS) VALUES ('DatabaseVendor', 'Vendor del database', 8, 0, 0, 0, 0, 'THIP_PERS', <USER>, <CURTS>, 'N');
    # ENUM
    INSERT INTO <SCH>ENUM (ATTRIBUTE_REF, ENUM_VALUE, SEQUENCE, ICON_NAME, BACK_COLOR, FORE_COLOR) VALUES ('DatabaseVendor', '1', 0, null, null, null);
    INSERT INTO <SCH>ENUM (ATTRIBUTE_REF, ENUM_VALUE, SEQUENCE, ICON_NAME, BACK_COLOR, FORE_COLOR) VALUES ('DatabaseVendor', '2', 0, null, null, null);
    INSERT INTO <SCH>ENUM (ATTRIBUTE_REF, ENUM_VALUE, SEQUENCE, ICON_NAME, BACK_COLOR, FORE_COLOR) VALUES ('DatabaseVendor', '3', 0, null, null, null);
    # ENUM_NLS
    INSERT INTO <SCH>ENUM_NLS (ATTRIBUTE_REF, ENUM_VALUE, LANGUAGE, ENUM_DESC) VALUES ('DatabaseVendor', '1', 'it', 'SQL Server');
    INSERT INTO <SCH>ENUM_NLS (ATTRIBUTE_REF, ENUM_VALUE, LANGUAGE, ENUM_DESC) VALUES ('DatabaseVendor', '2', 'it', 'DB2');
    INSERT INTO <SCH>ENUM_NLS (ATTRIBUTE_REF, ENUM_VALUE, LANGUAGE, ENUM_DESC) VALUES ('DatabaseVendor', '3', 'it', 'DB2 AS400');
    # ATTRIBUTE_REF_EXTENSION_DATA
    INSERT INTO <SCH>EXT_ATTRIBUTE_REF (ATTRIBUTE_REF, COLUMN_TYPE, REMARKS) VALUES ('DatabaseVendor', '0', null);
 
# Riferimento: GestoreProgetto
    # ATTRIBUTE_REF
    INSERT INTO <SCH>ATTRIBUTE_REF (ATTRIBUTE_REF, DESCRIPTION, TYPE_ID, TYPE_SIZE, TYPE_SCALE, DB_SIZE, DB_SCALE, FW_DATA_OWNER_ID, USER_ID, TIMESTAMP, ONLY_ICON_ON_WEBGRIDS) VALUES ('GestoreProgetto', 'Gestore progetto personalizzato', 8, 0, 0, 0, 0, 'THIP_PERS', <USER>, <CURTS>, 'N');
    # ENUM
    INSERT INTO <SCH>ENUM (ATTRIBUTE_REF, ENUM_VALUE, SEQUENCE, ICON_NAME, BACK_COLOR, FORE_COLOR) VALUES ('GestoreProgetto', '0', 0, null, null, null);
    INSERT INTO <SCH>ENUM (ATTRIBUTE_REF, ENUM_VALUE, SEQUENCE, ICON_NAME, BACK_COLOR, FORE_COLOR) VALUES ('GestoreProgetto', '1', 0, null, null, null);
    INSERT INTO <SCH>ENUM (ATTRIBUTE_REF, ENUM_VALUE, SEQUENCE, ICON_NAME, BACK_COLOR, FORE_COLOR) VALUES ('GestoreProgetto', '2', 0, null, null, null);
    # ENUM_NLS
    INSERT INTO <SCH>ENUM_NLS (ATTRIBUTE_REF, ENUM_VALUE, LANGUAGE, ENUM_DESC) VALUES ('GestoreProgetto', '0', 'it', 'Softre');
    INSERT INTO <SCH>ENUM_NLS (ATTRIBUTE_REF, ENUM_VALUE, LANGUAGE, ENUM_DESC) VALUES ('GestoreProgetto', '1', 'it', 'Sisthema');
    INSERT INTO <SCH>ENUM_NLS (ATTRIBUTE_REF, ENUM_VALUE, LANGUAGE, ENUM_DESC) VALUES ('GestoreProgetto', '2', 'it', 'Partner terzo');
    # ATTRIBUTE_REF_EXTENSION_DATA
    INSERT INTO <SCH>EXT_ATTRIBUTE_REF (ATTRIBUTE_REF, COLUMN_TYPE, REMARKS) VALUES ('GestoreProgetto', '0', null);
 
# Riferimento: TipoAmbiente
    # ATTRIBUTE_REF
    INSERT INTO <SCH>ATTRIBUTE_REF (ATTRIBUTE_REF, DESCRIPTION, TYPE_ID, TYPE_SIZE, TYPE_SCALE, DB_SIZE, DB_SCALE, FW_DATA_OWNER_ID, USER_ID, TIMESTAMP, ONLY_ICON_ON_WEBGRIDS) VALUES ('TipoAmbiente', 'Tipo ambiente personalizzazioni', 8, 0, 0, 0, 0, 'THIP_PERS', <USER>, <CURTS>, 'N');
    # ENUM
    INSERT INTO <SCH>ENUM (ATTRIBUTE_REF, ENUM_VALUE, SEQUENCE, ICON_NAME, BACK_COLOR, FORE_COLOR) VALUES ('TipoAmbiente', '-', 0, null, null, null);
    INSERT INTO <SCH>ENUM (ATTRIBUTE_REF, ENUM_VALUE, SEQUENCE, ICON_NAME, BACK_COLOR, FORE_COLOR) VALUES ('TipoAmbiente', 'A', 0, null, null, null);
    INSERT INTO <SCH>ENUM (ATTRIBUTE_REF, ENUM_VALUE, SEQUENCE, ICON_NAME, BACK_COLOR, FORE_COLOR) VALUES ('TipoAmbiente', 'L', 0, null, null, null);
    INSERT INTO <SCH>ENUM (ATTRIBUTE_REF, ENUM_VALUE, SEQUENCE, ICON_NAME, BACK_COLOR, FORE_COLOR) VALUES ('TipoAmbiente', 'S', 0, null, null, null);
    # ENUM_NLS
    INSERT INTO <SCH>ENUM_NLS (ATTRIBUTE_REF, ENUM_VALUE, LANGUAGE, ENUM_DESC) VALUES ('TipoAmbiente', '-', 'it', 'Non significativo');
    INSERT INTO <SCH>ENUM_NLS (ATTRIBUTE_REF, ENUM_VALUE, LANGUAGE, ENUM_DESC) VALUES ('TipoAmbiente', 'A', 'it', 'Altro');
    INSERT INTO <SCH>ENUM_NLS (ATTRIBUTE_REF, ENUM_VALUE, LANGUAGE, ENUM_DESC) VALUES ('TipoAmbiente', 'L', 'it', 'Locale');
    INSERT INTO <SCH>ENUM_NLS (ATTRIBUTE_REF, ENUM_VALUE, LANGUAGE, ENUM_DESC) VALUES ('TipoAmbiente', 'S', 'it', 'Server');
    # ATTRIBUTE_REF_EXTENSION_DATA
    INSERT INTO <SCH>EXT_ATTRIBUTE_REF (ATTRIBUTE_REF, COLUMN_TYPE, REMARKS) VALUES ('TipoAmbiente', '0', null);
