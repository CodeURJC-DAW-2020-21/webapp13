package es.webapp13.porthub.utils;

import es.webapp13.porthub.model.PortfolioItem;

import java.sql.SQLException;

public interface ImageUpdater {
    boolean run(PortfolioItem p) throws SQLException;
}