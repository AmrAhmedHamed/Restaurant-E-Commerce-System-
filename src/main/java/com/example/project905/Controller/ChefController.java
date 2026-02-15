package com.example.project905.Controller;

import com.example.project905.Dto.ChefDto;
import com.example.project905.Service.ChefService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
@Tag(name = "Chef Management", description = "APIs for managing chefs")
public class ChefController {

    private final ChefService chefService;

    @Autowired
    public ChefController(ChefService chefService) {
        this.chefService = chefService;
    }

    @Operation(summary = "Get all chefs", description = "Retrieve all chefs")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved chefs"),
            @ApiResponse(responseCode = "404", description = "No chefs found")
    })
    @GetMapping("/ChefController/getAllChefs")
    public ResponseEntity<List<ChefDto>> getAllChefs() {
        return ResponseEntity.ok(chefService.getAllChefs());
    }

    @Operation(summary = "Create multiple chefs", description = "Create multiple chefs at once")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully created chefs"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping("/ChefController/saveAll")
    public ResponseEntity<List<ChefDto>> saveAll(@Valid @RequestBody List<ChefDto> chefDtos) {
        return ResponseEntity.ok(chefService.saveList(chefDtos));
    }
}