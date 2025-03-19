package com.turkcell.analyticservice.controllers;



import an.awesome.pipelinr.Pipeline;
import com.turkcell.analyticservice.application.packages.command.create.CreatePackageCommand;
import com.turkcell.analyticservice.application.packages.command.create.CreatedPackageResponse;
import com.turkcell.analyticservice.application.packages.command.delete.DeletePackageCommand;
import com.turkcell.analyticservice.application.packages.command.delete.DeletedPackageResponse;
import com.turkcell.analyticservice.application.packages.command.update.UpdatePackageCommand;
import com.turkcell.analyticservice.application.packages.command.update.UpdatedPackageResponse;
import com.turkcell.analyticservice.application.packages.query.getbyid.GetPackageByIdItemDto;
import com.turkcell.analyticservice.application.packages.query.getbyid.GetPackageByIdQuery;
import com.turkcell.analyticservice.application.packages.query.list.GetListPackageItemDto;
import com.turkcell.analyticservice.application.packages.query.list.GetListPackageQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/packages")
@RequiredArgsConstructor
public class PackageController {
    private final Pipeline pipeline;


    @PostMapping
    public ResponseEntity<CreatedPackageResponse> createPackage(@RequestBody CreatePackageCommand command) {
        return ResponseEntity.ok(pipeline.send(command));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdatedPackageResponse> updatePackage(
            @PathVariable UUID id, @RequestBody UpdatePackageCommand command) {
        command.setId(id);
        return ResponseEntity.ok(pipeline.send(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeletedPackageResponse> deletePackage(@PathVariable UUID id) {
        return ResponseEntity.ok(pipeline.send(new DeletePackageCommand(id)));
    }

    @GetMapping
    public ResponseEntity<List<GetListPackageItemDto>> getListPackages() {
        GetListPackageQuery query = new GetListPackageQuery();
        List<GetListPackageItemDto> packages = pipeline.send(query);
        return ResponseEntity.ok(packages);
    }
    @GetMapping("/{id}")
    public ResponseEntity<GetPackageByIdItemDto> getPackageById(@PathVariable UUID id) {
        GetPackageByIdQuery query = new GetPackageByIdQuery(id);
        GetPackageByIdItemDto pkg = pipeline.send(query);
        return ResponseEntity.ok(pkg);
    }
}
