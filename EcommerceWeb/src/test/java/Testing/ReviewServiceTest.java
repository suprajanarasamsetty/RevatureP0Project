package Testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.Exception.ReviewCreateException;
import com.Exception.ReviewDeleteException;
import com.Exception.ReviewNotFoundException;
import com.Exception.ReviewUpdateException;
import com.dao.ReviewDAOClass;
import com.dto.ReviewRequest;
import com.dto.ReviewResponse;
import com.service.ReviewService;

public class ReviewServiceTest {
	
	@Mock
    private ReviewDAOClass reviewDao;

    @InjectMocks
    private ReviewService reviewService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetReviewById() throws SQLException, ReviewNotFoundException {
        // Arrange
        ReviewResponse mockReview = new ReviewResponse(1, 1, 1, 5, "Excellent product!");
        when(reviewDao.getReviewById(1)).thenReturn(mockReview);

        // Act
        ReviewResponse review = reviewService.getReviewById(1);

        // Assert
        assertEquals(mockReview, review);
        verify(reviewDao, times(1)).getReviewById(1);
    }

    @Test
    void testCreateReview() throws ReviewCreateException {
        // Arrange
        ReviewRequest reviewRequest = new ReviewRequest(1, 1, 1, 5, "Great product!");
        when(reviewDao.CreateReview(reviewRequest)).thenReturn(true);

        // Act
        boolean result = reviewService.CreateReview(reviewRequest);

        // Assert
        assertEquals(true, result);
        verify(reviewDao, times(1)).CreateReview(reviewRequest);
    }

    @Test
    void testUpdateReview() throws ReviewUpdateException {
        // Arrange
        ReviewRequest reviewRequest = new ReviewRequest(1, 1, 1, 4, "Good product, but could be better.");
        when(reviewDao.UpdateReview(reviewRequest)).thenReturn(true);

        // Act
        boolean result = reviewService.UpdateReview(reviewRequest);

        // Assert
        assertEquals(true, result);
        verify(reviewDao, times(1)).UpdateReview(reviewRequest);
    }

    @Test
    void testDeleteReview() throws ReviewDeleteException {
        // Arrange
        when(reviewDao.DeleteReview(1)).thenReturn(true);

        // Act
        boolean result = reviewService.DeleteReview(1);

        // Assert
        assertEquals(true, result);
        verify(reviewDao, times(1)).DeleteReview(1);
    }

    @Test
    void testGetAllReviews() throws ReviewNotFoundException {
        // Arrange
        ReviewResponse review1 = new ReviewResponse(1, 1, 1, 5, "Excellent product!");
        ReviewResponse review2 = new ReviewResponse(2, 2, 2, 4, "Very good product.");
        List<ReviewResponse> mockReviewList = Arrays.asList(review1, review2);
        when(reviewDao.getAllReviews()).thenReturn(mockReviewList);

        // Act
        List<ReviewResponse> reviews = reviewService.getAllReviews();

        // Assert
        assertEquals(mockReviewList, reviews);
        verify(reviewDao, times(1)).getAllReviews();
    }

    @Test
    void testGetReviewByIdThrowsException() throws SQLException, ReviewNotFoundException {
        // Arrange
        when(reviewDao.getReviewById(1)).thenThrow(new ReviewNotFoundException("Review not found"));

        // Act & Assert
        assertThrows(ReviewNotFoundException.class, () -> reviewService.getReviewById(1));
        verify(reviewDao, times(1)).getReviewById(1);
    }

    @Test
    void testCreateReviewThrowsException() throws ReviewCreateException {
        // Arrange
        ReviewRequest reviewRequest = new ReviewRequest(1, 1, 1, 5, "Great product!");
        when(reviewDao.CreateReview(reviewRequest)).thenThrow(new ReviewCreateException("Review creation failed"));

        // Act & Assert
        assertThrows(ReviewCreateException.class, () -> reviewService.CreateReview(reviewRequest));
        verify(reviewDao, times(1)).CreateReview(reviewRequest);
    }

    @Test
    void testUpdateReviewThrowsException() throws ReviewUpdateException {
        // Arrange
        ReviewRequest reviewRequest = new ReviewRequest(1, 1, 1, 4, "Good product, but could be better.");
        when(reviewDao.UpdateReview(reviewRequest)).thenThrow(new ReviewUpdateException("Review update failed"));

        // Act & Assert
        assertThrows(ReviewUpdateException.class, () -> reviewService.UpdateReview(reviewRequest));
        verify(reviewDao, times(1)).UpdateReview(reviewRequest);
    }

    @Test
    void testDeleteReviewThrowsException() throws ReviewDeleteException {
        // Arrange
        when(reviewDao.DeleteReview(1)).thenThrow(new ReviewDeleteException("Review deletion failed"));

        // Act & Assert
        assertThrows(ReviewDeleteException.class, () -> reviewService.DeleteReview(1));
        verify(reviewDao, times(1)).DeleteReview(1);
    }

}
