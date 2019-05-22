package com.example.model;

import com.example.enums.Status;
import com.example.utils.JsonUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public abstract class BaseEntity {

    private Integer displayOrder;

    private Status status;

    private Long createBy;

    private Date createDate;

    private Long lastModifiedBy;

    private Date lastModifiedDate;

    @JsonIgnore
    private Integer version;

    @JsonIgnore
    private Date lastUpdate;

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(Long lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((createBy == null) ? 0 : createBy.hashCode());
        result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
        result = prime * result + ((displayOrder == null) ? 0 : displayOrder.hashCode());
        result = prime * result + ((lastUpdate == null) ? 0 : lastUpdate.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((lastModifiedBy == null) ? 0 : lastModifiedBy.hashCode());
        result = prime * result + ((lastModifiedDate == null) ? 0 : lastModifiedDate.hashCode());
        result = prime * result + ((version == null) ? 0 : version.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        BaseEntity other = (BaseEntity) obj;

        if (createBy == null) {
            if (other.createBy != null) {
                return false;
            }
        } else if (!createBy.equals(other.createBy)) {
            return false;
        }

        if (createDate == null) {
            if (other.createDate != null) {
                return false;
            }
        } else if (!createDate.equals(other.createDate)) {
            return false;
        }

        if (displayOrder == null) {
            if (other.displayOrder != null) {
                return false;
            }
        } else if (!displayOrder.equals(other.displayOrder)) {
            return false;
        }

        if (lastUpdate == null) {
            if (other.lastUpdate != null) {
                return false;
            }
        } else if (!lastUpdate.equals(other.lastUpdate)) {
            return false;
        }

        if (status != other.status) {
            return false;
        }

        if (lastModifiedBy == null) {
            if (other.lastModifiedBy != null) {
                return false;
            }
        } else if (!lastModifiedBy.equals(other.lastModifiedBy)) {
            return false;
        }

        if (lastModifiedDate == null) {
            if (other.lastModifiedDate != null) {
                return false;
            }
        } else if (!lastModifiedDate.equals(other.lastModifiedDate)) {
            return false;
        }

        if (version == null) {
            if (other.version != null) {
                return false;
            }
        } else if (!version.equals(other.version)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return JsonUtil.toJsonQuietly(this);
    }
}
