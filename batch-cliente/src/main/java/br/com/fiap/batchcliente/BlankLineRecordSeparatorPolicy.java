package br.com.fiap.batchcliente;

import org.springframework.batch.item.file.separator.SimpleRecordSeparatorPolicy;

/**
 * BlankLineRecordSeparatorPolicy
 */
public class BlankLineRecordSeparatorPolicy  extends SimpleRecordSeparatorPolicy {

    @Override
    public boolean isEndOfRecord(String line) {
        if (line.startsWith("-"))
            return false;
        else if (line.trim().length() == 0)
            return false;
        else
            return super.isEndOfRecord(line);
    }

    @Override
    public String postProcess(String record) {
        if (record == null || record.trim().length() == 0)
            return null;

        return super.postProcess(record);
    }

    @Override
    public String preProcess(String line) {
        if (line.startsWith("-"))
            return "";

        return super.preProcess(line);
    }
}