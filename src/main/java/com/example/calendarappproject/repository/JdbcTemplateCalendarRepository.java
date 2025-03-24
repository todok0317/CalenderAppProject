package com.example.calendarappproject.repository;

import com.example.calendarappproject.dto.CalendarResponseDto;
import com.example.calendarappproject.entity.Calendar;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class JdbcTemplateCalendarRepository implements CalendarRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateCalendarRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public CalendarResponseDto saveToDo(Calendar calendar) {

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("calendar").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("author", calendar.getAuthor());
        parameters.put("password", calendar.getPassword());
        parameters.put("title", calendar.getTitle());
        parameters.put("todo", calendar.getTodo());
        parameters.put("createdAt", LocalDateTime.now());
        parameters.put("updatedAt", null);

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new CalendarResponseDto(
                key.longValue(),
                calendar.getAuthor(),
                calendar.getPassword(),
                calendar.getTitle(),
                calendar.getTodo(),
                LocalDateTime.now(),
                null);
    }
    // 할 일 목록 조회
    @Override
    public List<CalendarResponseDto> findAllCalendars() {
        return jdbcTemplate.query("SELECT * FROM calendar", calendarsRowMapper());
    }


    private RowMapper<CalendarResponseDto> calendarsRowMapper() {
        return new RowMapper<CalendarResponseDto>() {
            @Override
            public CalendarResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new CalendarResponseDto(
                        rs.getLong("id"),
                        rs.getString("author"),
                        rs.getString("password"),
                        rs.getString("title"),
                        rs.getString("todo"),
                        rs.getTimestamp("createdAt").toLocalDateTime(),
                        rs.getTimestamp("updatedAt") != null ? rs.getTimestamp("updatedAt").toLocalDateTime() : null
                );
            }
        };
    }

    // 선택 할 일 조회
    @Override
    public Optional<Calendar> findCalendarById(Long id) {
        List<Calendar> result = jdbcTemplate.query("SELECT * FROM calendar WHERE id = ?", memoRowMapperV2(), id);
        return result.stream().findAny();
    }

    @Override
    public Calendar findCalendarByIdOrElseThrow(Long id) {
        List<Calendar> result = jdbcTemplate.query("SELECT * FROM calendar WHERE id = ?", memoRowMapperV2(), id);
        return result.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "아이디를 찾을 수 없습니다 id = " + id));
    }


    private RowMapper<Calendar> memoRowMapperV2() {
        return new RowMapper<Calendar>() {
            @Override
            public Calendar mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Calendar(
                        rs.getLong("id"),
                        rs.getString("author"),
                        rs.getString("todo"),
                        rs.getString("password")
                );
            }
        };
    }

    // 선택한 일정 수정
    @Override
    public int updateCalendar(Long id, String author, String todo) {
        return jdbcTemplate.update("UPDATE calendar SET author = ?,todo = ? WHERE id = ?", author, todo, id);
    }



}
