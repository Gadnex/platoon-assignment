package net.binarypaper.platoonassignment.service;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.net.URI;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.extern.java.Log;
import net.binarypaper.platoonassignment.entity.Cic;
import net.binarypaper.platoonassignment.entity.Entity;
import net.binarypaper.platoonassignment.exception.BadRequestException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(path = "cic", produces = {MediaType.APPLICATION_JSON_VALUE})
@Api(tags = {"Cic"})
@Log
public class CicService {

    @PersistenceContext
    private EntityManager em;

    @PostMapping
    @Transactional
    @JsonView(value = Cic.View.Detail.class)
    @ApiOperation(value = "Register a cic",
            notes = "Register a cic",
            code = 201,
            response = Cic.class
    )
    public ResponseEntity<Cic> registerCic(
            @JsonView(value = Cic.View.Add.class)
            @RequestBody Cic cic) {
        log.log(Level.INFO, "Register Cic: {0}", cic);
        Long entityId = cic.getEntityId();
        Entity entity = em.find(Entity.class, entityId);
        if (entity == null) {
            throw new BadRequestException("Invalid entityId");
        }
        cic.setEntity(entity);
        em.persist(cic);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(cic.getCicId())
                .toUri();
        return ResponseEntity.created(location).body(cic);
    }

    @GetMapping(path = "{cicId}")
    @JsonView(value = Cic.View.Detail.class)
    @ApiOperation(value = "Retrieve cic info",
            notes = "Retrieve cic info",
            response = Cic.class
    )
    public ResponseEntity<Cic> retrieveCicInfo(Long cicId) {
        Cic cic = em.find(Cic.class, cicId);
        if (cic == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cic);
    }

}
