package com.akisreti.partnerregistry.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.akisreti.partnerregistry.domain.Partner;
import com.akisreti.partnerregistry.domain.PartnerType;
import com.akisreti.partnerregistry.dto.PartnerDto;
import com.akisreti.partnerregistry.service.DocumentService;
import com.akisreti.partnerregistry.service.PartnerService;

/**
 * AddressServiceImpl.
 *
 * @author kisretia
 */
@Service
public class DocumentServiceImpl implements DocumentService {

    public DocumentServiceImpl() {
    }

    @Override
    public byte[] downloadFile( final List<String> header, List<List<String>> records ) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try ( CSVPrinter csvPrinter = new CSVPrinter(new OutputStreamWriter(baos), CSVFormat.DEFAULT) ) {

            csvPrinter.printRecord(header);

            for ( List<String> record : records ) {
                csvPrinter.printRecord(record);
            }

            csvPrinter.flush();
        }

        return baos.toByteArray();
    }

    @Override
    public List<CSVRecord> uploadFile( final MultipartFile file ) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(file.getBytes());

        try ( CSVParser csvParser = new CSVParser(new InputStreamReader(bais), CSVFormat.DEFAULT) ) {
            List<CSVRecord> records = csvParser.getRecords();
            if ( !records.isEmpty() ) {
                records.remove(0);
            }
            return records;
        }
    }

}
