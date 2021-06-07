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

    private PartnerService partnerService;

    public DocumentServiceImpl( PartnerService partnerService ) {
        this.partnerService = partnerService;
    }

    @Override
    public byte[] downloadFile() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try ( CSVPrinter csvPrinter = new CSVPrinter(new OutputStreamWriter(baos), CSVFormat.DEFAULT) ) {

            csvPrinter.printRecord("partnerId", "name", "type");

            for ( PartnerDto p : partnerService.getPartnerList() ) {
                csvPrinter.printRecord(p.getPartnerId().toString(), p.getName(), p.getType().name());
            }

            csvPrinter.flush();
        }

        return baos.toByteArray();
    }

    @Override
    public void uploadFile( final MultipartFile file ) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(file.getBytes());
        CSVParser csvParser = new CSVParser(new InputStreamReader(bais), CSVFormat.DEFAULT);

        List<Partner> partners = new LinkedList<>();
        for ( CSVRecord record : csvParser.getRecords() ) {
            if ( record.getRecordNumber() > 1 ) {
                partners.add(
                    Partner.builder().partnerId(StringUtils.hasLength(record.get(0)) ? Long.parseLong(record.get(0)) : null).name(record.get(1))
                        .type(PartnerType.valueOf(record.get(2))).build());
            }
        }

        partnerService.savePartnerList(partners);
    }

}
