package com.globomantics.plots.controller;


import com.globomantics.plots.models.Plot;
import com.globomantics.plots.service.PlotsService;
import com.globomantics.plots.models.exceptions.BadRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/plots")
public class PlotsController {

    private final PlotsService plotsService;

    public PlotsController(PlotsService plotsService) {
        this.plotsService = plotsService;
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Plot> getAllPlots(){
        return plotsService.getAllPlots();
    }

    @GetMapping("/{plot-number}")
    @ResponseStatus(HttpStatus.OK)
    public Plot getPlotByNumber(
            @PathVariable("plot-number") Integer plotNumber) throws BadRequest {
        return plotsService.getPlot(plotNumber);
    }

    @PostMapping("/{plot-number}")
    @ResponseStatus(HttpStatus.CREATED)
    public Plot createPlot(
            @PathVariable("plot-number") Integer plotNumber, @RequestBody Plot plot) throws BadRequest {
        return plotsService.createPlot(plot, plotNumber);
    }

    @PutMapping("/{plot-number}")
    @ResponseStatus(HttpStatus.OK)
    public Plot updatePlot(
            @PathVariable("plot-number") Integer plotNumber, @RequestBody Plot plot) throws BadRequest {
        return plotsService.updatePlot(plot, plotNumber);
    }

    @DeleteMapping("/{plot-number}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePlot(
            @PathVariable("plot-number") Integer plotNumber) {
        plotsService.deletePlot(plotNumber);
    }
}
