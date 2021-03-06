/**
 *
 */

package com.mbk.model;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Getter;

/**
 * <p>
 * Base class for all entities.
 * </p>
 *
 * @author hrishi
 *
 */
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

  private static final long serialVersionUID = 2611065946454999464L;

  @Getter
  @Id
  @GeneratedValue
  private Long id;

}
