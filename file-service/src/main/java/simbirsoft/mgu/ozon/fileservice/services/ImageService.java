package simbirsoft.mgu.ozon.fileservice.services;

import lombok.RequiredArgsConstructor;
import org.bson.types.Binary;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import simbirsoft.mgu.ozon.fileservice.domain.FileEntity;
import simbirsoft.mgu.ozon.fileservice.dto.FileResponseDto;
import simbirsoft.mgu.ozon.fileservice.exception.FileException;
import simbirsoft.mgu.ozon.fileservice.repository.FileRepository;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final FileRepository fileRepository;

    public FileResponseDto upload(MultipartFile file) {

        try {
            FileEntity fileEntity = FileEntity.builder()
                    .fileName(file.getOriginalFilename())
                    .uploadDate(LocalDateTime.now())
                    .bytes(
                            new Binary(file.getBytes())
                    )
                    .build();

            fileEntity = fileRepository.save(fileEntity);

            FileResponseDto fileResponseDto = FileResponseDto.builder()
                    ._id(fileEntity.get_id())
                    .fileName(fileEntity.getFileName())
                    .build();
            return fileResponseDto;
        } catch (IOException e) {
            throw new FileException("Could not upload the file");
        }
    }

    public Resource download(String id) {
        FileEntity file = fileRepository.findById(id)
                .orElseThrow(() -> new FileException(String.format("File with id: %s not found", id)));

        Resource resource = new ByteArrayResource(file.getBytes().getData());
        return resource;
    }

    public String delete(String id) {
        FileEntity file = fileRepository.findById(id)
                .orElseThrow(() -> new FileException(String.format("File with id: %s not found", id)));

        fileRepository.delete(file);
        return String.format("File with id: %s deleted", id);
    }
}
