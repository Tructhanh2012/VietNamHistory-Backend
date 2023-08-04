package com.second.version.character;

import com.second.version.dto.request.FigureRequest;
import com.second.version.dto.request.IdRequest;
import com.second.version.event.Event;
import com.second.version.generation.Generation;
import com.second.version.generation.GenerationRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class FigureService {
    private FigureRepository figureRepository;
    private GenerationRepository generationRepository;
    public List<Figure> findAll(){
        return figureRepository.findAll(Sort.by(Sort.Direction.ASC,"generation_id"));
    }
    public Figure findById(long id){
        return figureRepository.findById(id).orElse(null);
    }
    public Figure createCharacter(FigureRequest request){
        if (request.getName() != null && !request.getName().isEmpty())
            if (figureRepository.findByName(request.getName()) == null){
                if (request.getDescription() != null && request.getGenerationId() != null &&
                        request.getBirthYear() != null && request.getPassedYear() != null &&
                        request.getImageLink() != null && !request.getImageLink().isEmpty() &&
                        !request.getDescription().isEmpty() && !request.getPassedYear().isEmpty() &&
                        !request.getBirthYear().isEmpty()) {
                    Generation generation = generationRepository.findById(request.getGenerationId()).orElse(null);
                    if (generation == null) return null;

                    Figure newFigure = new Figure(request.getName(), request.getDescription(),
                        request.getBirthYear(), request.getPassedYear(), request.getImageLink(), generation);
                    return figureRepository.save(newFigure);
                }
        }
        return null;
    }
    public Figure editFigure(FigureRequest request){
        if (request.getId() == null) return null;
        Figure existingfigure = figureRepository.findById(request.getId()).orElse(null);
        if (existingfigure != null){
            if (request.getName() != null && !request.getName().isEmpty())
                if (figureRepository.findByName(request.getName()) == null)
                    existingfigure.setName(request.getName());
            if (request.getDescription() != null && !request.getDescription().isEmpty())
                existingfigure.setDescription(request.getDescription());
            if (request.getBirthYear() != null && !request.getBirthYear().isEmpty())
                existingfigure.setBirthYear(request.getBirthYear());
            if (request.getPassedYear() != null && !request.getPassedYear().isEmpty())
                existingfigure.setPassedYear(request.getPassedYear());
            if (request.getImageLink() != null && !request.getImageLink().isEmpty())
                existingfigure.setImageLink(request.getImageLink());
            if (request.getGenerationId() != null){
                Generation generation = generationRepository.findById(request.getId()).orElse(null);
                if (generation != null)
                    existingfigure.setGeneration(generation);
            }
            return figureRepository.save(existingfigure);
        }
        return null;
    }
    public boolean deleteFigure(IdRequest idRequest){
        if (figureRepository.findById(idRequest.getId()).orElse(null) != null){
            figureRepository.deleteById(idRequest.getId());
            return true;
        }
        return false;
    }
}
