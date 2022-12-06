package com.globomantics.plots.repository;

import com.globomantics.plots.models.Plot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlotRepository extends JpaRepository<Plot, Integer> {

    Optional<Plot> findByPlotNumber(Integer plotNumber);

}
