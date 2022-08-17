package platform.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import platform.model.Code;
import platform.repository.CodeRepository;

import javax.transaction.Transactional;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CodeService  {
    private static final String DATE_FORMATTER = "yyyy/MM/dd HH:mm:ss";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
    @Autowired
    private CodeRepository codeRepository;

    public void updateViewById(UUID id) {
        Code updateCode = getCode(id);
        int views = updateCode.getViews();
        if (views > 0) {
            views--;
            updateCode.setViews(views);
            codeRepository.save(updateCode);
        } else {
            codeRepository.delete(updateCode);
        }
    }

    public void updateTimeById(UUID id) {
        Code updateCode = getCode(id);
        long diff = Duration.between(updateCode.getCreationDate(), LocalDateTime.now()).toSeconds();
        long time = updateCode.getTime() - diff;
        if (time > 0) {
            updateCode.setTime(time);
            codeRepository.save(updateCode);
        } else {
            codeRepository.delete(updateCode);
        }
    }

    public Code getCode(UUID id) {
        Optional<Code> codeOptional = codeRepository.findById(id);
        if (codeOptional.isPresent()) {
            return codeOptional.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Code not found.");
    }
    public Code saveCode(Code code) {
        if (code != null) {
            LocalDateTime localDateTime = LocalDateTime.now();
            code.setDate(localDateTime.format(formatter));
            code.setCreationDate(localDateTime);
            if (code.getViews() > 0) {
                code.setViewRestricted(true);
            }
            if (code.getTime() > 0) {
                code.setTimeRestricted(true);
            }
            return codeRepository.save(code);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unable to save code, invalid Code object.");
    }

    public List<Code> getLatestSnippets() {
        return codeRepository.findLatestSnippets();
    }
}
