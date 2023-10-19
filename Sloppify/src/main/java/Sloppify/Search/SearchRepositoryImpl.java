package Sloppify.Search;

import Sloppify.Track.SloppifyTrack;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.search.engine.search.query.SearchResult;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class SearchRepositoryImpl implements SearchRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<SloppifyTrack> searchBy(String text, int limit, String[] fields) {
        SearchSession searchSession = Search.session((Session) entityManager);

        SearchResult<SloppifyTrack> result = searchSession
                .search(SloppifyTrack.class)
                .where(f -> f.match().fields(fields).matching(text).fuzzy(2))
                .fetch(limit);

        return result.hits();
    }
}
